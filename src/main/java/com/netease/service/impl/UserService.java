package com.netease.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.IUserDao;
import com.netease.meta.User;
import com.netease.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	public IUserDao userdao;

	@Override
	public User getUser(String account, String password) {
		User user = userdao.getUserByAccount(account);
		User nUser = new User();
		if (user != null) {
			if (user.getPassword().equals(password)) {
				nUser.setName(user.getName());
				nUser.setType(user.getType());
				return nUser;
			}
		}
		return null;
	}
	
	public String MD5Tools(String password) {
		//byte[] passwordByte = hexStringToBytes(password);
		//String passwordMd5 = bytesToHexString(passwordByte);
		//return passwordMd5;
        char[] a = password.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
		
	}
	
    public byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
	
	
	public String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
	
    public byte charToByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }

}
