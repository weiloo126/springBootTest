package com.example.springBootTest.log4j2;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


/**
 * 我们在集群中部署应用之后，应用请求的日志被分散记录在了不同应用服务器的文件系统上，这样分散的存储并不利于我们对日志内容的检索。
 * 解决日志分散问题的方案多种多样，扩展log4j实现将日志写入MongoDB
 * 
 * 自定义appender实现
 * 思路：log4j提供的输出器实现自Appender接口，要自定义appender输出到MongoDB
 * 
 * 该类主要提供一个思路去实现自定义日志的输出和管理。我们可以通过jdbc实现日志记录到mongodb，也可以通过spring-boot-starter-data-mongodb来记录到mongodb，当然我们也可以输出到其他数据库，或者输出到消息队列等待其他后续处理等。
 * 对于日志记录到mongodb，也可以直接使用log4mongo（https://github.com/log4mongo/log4mongo-java）实现更为方便快捷。
 * 
 *   
 * @date 2017年10月25日
 */
//note: class name need not match the @Plugin name.
@Plugin(name="MongoAppender", category="Core", elementType="appender", printObject=true)
public final class MongoAppender extends AbstractAppender {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<BasicDBObject> logsCollection;

    private String connectionUrl;
    private String databaseName;
    private String collectionName;
	
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();

	protected MongoAppender(String name, String connectionUrl, String databaseName, String collectionName, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
		super(name, filter, layout, ignoreExceptions);
		this.connectionUrl = connectionUrl;
		this.databaseName = databaseName;
		this.collectionName = collectionName;
	}

	// The append method is where the appender does the work.
    // Given a log event, you are free to do with it what you want.
    // This example demonstrates:
    // 1. Concurrency: this method may be called by multiple threads concurrently
    // 2. How to use layouts
    // 3. Error handling
	@Override
	public void append(LogEvent event) {
		readLock.lock();
        try {
//            final byte[] bytes = getLayout().toByteArray(event);
//            System.out.write(bytes);
        	if(mongoDatabase == null) {
                MongoClientURI connectionStr = new MongoClientURI(connectionUrl);
                mongoClient = new MongoClient(connectionStr);
                mongoDatabase = mongoClient.getDatabase(databaseName);
                logsCollection = mongoDatabase.getCollection(collectionName, BasicDBObject.class);
            }
        	BasicDBObject bson = BasicDBObject.parse(event.getMessage().toString());
            logsCollection.insertOne(bson);
        } catch (Exception ex) {
//            if (!ignoreExceptions()) {
//                throw new AppenderLoggingException(ex);
//            }
        	ex.printStackTrace();
        } finally {
            readLock.unlock();
        }
	}
	
    // Your custom appender needs to declare a factory method
    // annotated with `@PluginFactory`. Log4j will parse the configuration
    // and call this factory method to construct an appender instance with
    // the configured attributes.
    @PluginFactory
    public static MongoAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginAttribute("connectionUrl") String connectionUrl,
            @PluginAttribute("databaseName") String databaseName,
            @PluginAttribute("collectionName") String collectionName,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter) {
        if (name == null) {
            LOGGER.error("No name provided for MongoAppender");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new MongoAppender(name, connectionUrl, databaseName, collectionName, filter, layout, true);
    }
    
//    @Override
//    public void close() {
//        if(mongoClient != null) {
//            mongoClient.close();
//        }
//    }
    
    
}
