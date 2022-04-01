//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//    	
//    	http.authorizeRequests()
//    	.antMatchers("/oauth2/**", "/login/**", "/monitoramento","/").permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .oauth2Login()
//        .defaultSuccessUrl( "/" );
//    }
//    
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/externo/**",  "/js/**", "/img/**", "/icon/**");
//	}
//    
//}
