package cn.yixianen.core.service;

import cn.yixianen.core.pojo.seller.Seller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * @author yixianen
 * @Date 2020/12/6 15:37
 * @version 1.0
 */
public class UserDetailServiceImpl implements UserDetailsService {
    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        grantedAuthoritiesList.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        if (username != null){
            Seller s = sellerService.findOne(username);
            if (s != null){
                if ("1".equals(s.getStatus())){
                    return new User(s.getName(), s.getPassword(), grantedAuthoritiesList);
                }
            }
        }
        return null;
    }
}
