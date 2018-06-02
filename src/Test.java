import java.security.MessageDigest;

public class Test {

	public static void main(String[] args) throws Exception {
		//String data ="appId=wx674bcd7a0bcca627&noceStr=DC388CE04E40416D8A6DE8DCBDDE4B3A&package=prepay_id=wx122041135799144ea6aecf280555596484&signType=MD5&timeStamp=1526128885&key=tengyueorilore033530288502018050";
		String data = "appId=wx674bcd7a0bcca627&noceStr=9C99A8B5C6AD40F3AB8093560047229F&package=prepay_id=wx122104192954884ea6aecf281681764889&signType=MD5&timeStamp=1526130262&key=tengyueorilore033530288502018050";
		java.security.MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        System.out.println(sb.toString().toUpperCase());
	}

}
