//package com.chen.xiansen.r2dbc.conn;
//
//import com.chen.xiansen.r2dbc.entity.TAuther;
//import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration;
//import io.asyncer.r2dbc.mysql.MySqlConnectionFactory;
//import reactor.core.publisher.Mono;
//
//import java.io.IOException;
//
///**
// * 有了r2dbc,应用在数据库层面天然支持高并发,高吞吐量;
// * 并不能提升开发效率
// */
//public class R2DBCConnection {
//    public static void main(String[] args) throws IOException {
//        MySqlConnectionConfiguration database = MySqlConnectionConfiguration
//                .builder()
//                .host("192.168.10.46")
//                .port(3306)
//                .username("root")
//                .password("123456")
//                .database("test")
//                .build();
//        //r2dbc基于全异步,响应式,消息驱动
//        //获取连接
//        MySqlConnectionFactory factory = MySqlConnectionFactory.from(database);
//        //获取连接,发送sql
//        //数据发布者
//        Mono.from(factory.create()).flatMapMany(connection -> connection.createStatement(
//                        "select * from t_author where  id = ?").bind(0, 1L).execute())
//                .flatMap(result -> result.map(
////                        (row, rowMetadata) -> {
////                    Long id = row.get("id", Long.class);
////                    String name = row.get("name", String.class);
////                    return new TAuther(id, name);
////                }
//                        readable -> {
//                            Long id = readable.get("id", Long.class);
//                            String name = readable.get("name", String.class);
//                            return new TAuther(id, name);
//                        }
//                )).doOnError(err -> System.out.println("err = " + err))
//                .subscribe(tAuther -> System.out.println("tAuther = " + tAuther));
//
//        System.in.read();
//    }
//}
