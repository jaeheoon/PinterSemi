package spring.conf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.board.bean.BoardDTO;
import com.boardscrap.bean.BoardScrapDTO;
import com.comment.bean.CommentDTO;
import com.member.bean.MemberDTO;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:spring/db.properties")
@MapperScan("com.member.dao com.board.dao com.comment.dao com.boardscrap.dao")
public class SpringConfiguration {
	private @Value("${jdbc.driver}") String driver;
	private @Value("${jdbc.url}") String url;
	private @Value("${jdbc.username}") String username;
	private @Value("${jdbc.password}") String password;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public List<MemberDTO> arrayList() {
		return new ArrayList<MemberDTO>();
	}
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		return basicDataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*Mapper.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("*.*.bean");
		return sqlSessionFactoryBean.getObject();	//sqlSessionFactory 변환
	}
	
	@Bean
	public SqlSessionTemplate sqlTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
		return dataSourceTransactionManager;
	}
	
	@Bean
	@Scope("prototype")
	public MemberDTO userDTO() {
		return new MemberDTO();
	}
	
	@Bean
	@Scope("prototype")
	public BoardDTO boardDTO() {
		return new BoardDTO();
	}
	
	@Bean
	@Scope("prototype")
	public CommentDTO commentDTO() {
		return new CommentDTO();
	}
	
	@Bean
	@Scope("prototype")
	public BoardScrapDTO boardScrapDTO() {
		return new BoardScrapDTO();
	}
}
