package com.maqv.code.generator.database.mysql;

import com.maqv.code.generator.G;
import com.maqv.code.generator.PropertiesKey;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyin
 * @create 2019-12-15 13:20
 **/
public class JdbcUtil {


        public JdbcUtil() {
        }




        private static Connection getConnection() throws SQLException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection(G.getProperties(PropertiesKey.JdbcUrlKey),
                    G.getProperties(PropertiesKey.UsernameKey),
                    G.getProperties(PropertiesKey.PasswordKey)
                    );
        }




        public static String getTableSchema(){
            String jdbcURL = G.getProperties(PropertiesKey.JdbcUrlKey);
            int i = jdbcURL.indexOf("?");
            int i1 = jdbcURL.lastIndexOf("/");
            return  jdbcURL.substring(i1+1, i);
        }


        public static List<MysqlColumnInfo> queryColumns(){
            String sql=" SELECT c.*,t.TABLE_COMMENT FROM `information_schema`.`COLUMNS` c ,`information_schema`.TABLES t WHERE c.`TABLE_SCHEMA` = ? and c.TABLE_NAME=t.TABLE_NAME;";
            try {
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                String tableSchema=getTableSchema();
                preparedStatement.setString(1,tableSchema);
                ResultSet rs = preparedStatement.executeQuery();
                ResultSetMetaData metaData = rs.getMetaData();
//                int columnCount = metaData.getColumnCount();
//                for (int i = 1; i <= columnCount; i++) {
//                    String columnName = metaData.getColumnName(i);
//                    System.out.println(columnName);
//                }


                List<MysqlColumnInfo> result=new ArrayList<>();
                while(rs.next()){
                    String column_name = rs.getString("COLUMN_NAME");
                    String table_name = rs.getString("TABLE_NAME");
                    Integer ordinal_position = rs.getInt("ORDINAL_POSITION");
                    String is_nullable = rs.getString("IS_NULLABLE");
                    String data_type = rs.getString("DATA_TYPE");
                    Integer character_maximum_length = rs.getInt("CHARACTER_MAXIMUM_LENGTH");
                    Integer character_octet_length = rs.getInt("CHARACTER_OCTET_LENGTH");
                    Integer numeric_precidion = rs.getInt("NUMERIC_PRECISION");
                    Integer numeric_scale = rs.getInt("NUMERIC_SCALE");
                    String column_type = rs.getString("COLUMN_TYPE");
                    String column_key = rs.getString("COLUMN_KEY");
                    String extra = rs.getString("EXTRA");
                    String column_comment = rs.getString("COLUMN_COMMENT");
                    String table_comment = rs.getString("TABLE_COMMENT");
                    MysqlColumnInfo mysqlColumnInfo = new MysqlColumnInfo(column_name, table_name, ordinal_position, is_nullable, data_type, character_maximum_length, character_octet_length,
                            numeric_precidion, numeric_scale, column_type, column_key, extra, column_comment, table_comment);
                    result.add(mysqlColumnInfo);
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("查询列信息失败");
        }

    public static void main(String[] args) {
        queryColumns();
    }
}
