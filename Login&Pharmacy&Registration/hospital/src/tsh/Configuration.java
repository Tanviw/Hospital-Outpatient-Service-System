package tsh;
/**
 * 管理配置信息
 * @author Tanvi
 *
 */
public class Configuration {

	private String driver;		//驱动类
	private	String url;			//JDBC的url
	private String user;		//数据库的用户名
	private String pwd;			//数据库的密码
	
	private int poolMaxSize;	//连接池中最大的连接数
	private int	poolMinSize;	//连接池中最小的连接数
	
	public int getPoolMaxSize() {
		return poolMaxSize;
	}
	public void setPoolMaxSize(int poolMaxSize) {
		this.poolMaxSize = poolMaxSize;
	}
	public int getPoolMinSize() {
		return poolMinSize;
	}
	public void setPoolMinSize(int poolMinSize) {
		this.poolMinSize = poolMinSize;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public Configuration(String driver, String url, String user, String pwd
			) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		
	}
	public Configuration() {
	}
}
