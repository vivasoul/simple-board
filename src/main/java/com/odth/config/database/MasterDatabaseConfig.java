package com.odth.config.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(annotationClass = Mapper.class
		, sqlSessionFactoryRef = "masterSqlSessionFactory"
		, value = {"com.odth"}
)
public class MasterDatabaseConfig {

	@Value("${spring.datasource.hikari.master.mybatis.config-location}")
	private String mybatisConfigLocation;

    @Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari.master")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	@Primary
	@Bean(name = "masterDataSource")
	public DataSource masterDataSource() {
		return new HikariDataSource(hikariConfig());
	}

	@Primary
	@Bean(name = "masterSqlSessionFactory")
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

		Resource[] arrResource = resolver.getResources("classpath*:/mappers/**/*.xml");
		sqlSessionFactoryBean.setMapperLocations(arrResource);
		sqlSessionFactoryBean.setConfigLocation(resolver.getResource(mybatisConfigLocation));

		return sqlSessionFactoryBean.getObject();
	}

	@Primary
	@Bean(name = "masterSqlSessionTemplate")
	public SqlSessionTemplate masterSqlSessionTemplate(
			@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Primary
	@Bean(name = "masterTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    @Bean(name = "atomikosMainBean", initMethod = "init", destroyMethod = "close")
//    public AtomikosDataSourceBean mainAtomikosBean() {
//    	DataSourceProperties props = dataSourceProp();
//
//        Properties xaProps = new Properties();
//        xaProps.setProperty("URL", props.getUrl());
//        xaProps.setProperty("user", props.getUsername());
//        xaProps.setProperty("password", props.getPassword());
//
//    	AtomikosDataSourceBean atomikosBean = new AtomikosDataSourceBean();
//    	atomikosBean.setUniqueResourceName("mainAtomikos");
//    	atomikosBean.setXaDataSourceClassName(props.getDriverClassName());
//    	//atomikosDsBean.setMaxPoolSize(10)
//    	//atomikosDsBean.setMinPoolSize(5);
//    	//atomikosDsBean.setTestQuery("SELECT 1 FROM dual");
//    	atomikosBean.setDefaultIsolationLevel(Connection.TRANSACTION_READ_COMMITTED);
//    	atomikosBean.setXaProperties(xaProps);
//
//    	return atomikosBean;
//    }
//
//    @Bean("mainDataSource")
//    public DataSource mainDataSource(@Qualifier("atomikosMainBean") AtomikosDataSourceBean atomikosBean) {
//
//    	return new DataSourceSpy(atomikosBean);
//    }
//
//	@Bean("masterSqlSessionFactory")
//	public SqlSessionFactory sqlSessionFactoryBean(ApplicationContext applicationContext,
//												@Qualifier("mainDataSource") DataSource datasource) throws Exception{
//
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(datasource);
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/wms/**/*.xml"));
//        sqlSessionFactoryBean.setPlugins(new NexacroMybatisMetaDataProvider(),new NexacroMybatisResultSetHandler());
//
//		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
//		sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
//		sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
//
//		return sqlSessionFactory;
//	}
}
