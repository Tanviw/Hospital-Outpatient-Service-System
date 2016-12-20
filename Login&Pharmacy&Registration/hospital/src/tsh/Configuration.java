package tsh;
/**
 * ����������Ϣ
 * @author Tanvi
 *
 */
public class Configuration {

	private String driver;		//������
	private	String url;			//JDBC��url
	private String user;		//���ݿ���û���
	private String pwd;			//���ݿ������
	
	private int poolMaxSize;	//���ӳ�������������
	private int	poolMinSize;	//���ӳ�����С��������
	
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
