package com.Abhishek.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Abhishek.Services.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

   @Autowired
    private JWTService jwtservice;

    @Autowired
    private UserDetailsService userDetailsService;

     
    @Override
    protected void doFilterInternal( HttpServletRequest request, 
                                    HttpServletResponse response, 
                                     FilterChain filterChain)
                                    throws ServletException, IOException {
        // TODO Auto-generated method stub

        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        // here is space after Bearer because format of jwt is "Bearer 12nibdx277uilmyodz72b873uyo...whatever"
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        // because the Bearer_ is 7 letters
        jwt=authHeader.substring(7);
        //System.out.println(jwt);
        userEmail=jwtservice.extractUsername(jwt);
       // System.out.println(userEmail);
        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(userEmail);
            // System.out.println("I am In"); 

               

            if(jwtservice.isTokenVaild(jwt, userDetails)){
                        // System.out.println("Here in Token Valid");
                UsernamePasswordAuthenticationToken authtoken= new UsernamePasswordAuthenticationToken
                (userDetails,
                null,
                userDetails.getAuthorities()
                    );
                 authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 
                 SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
            filterChain.doFilter(request, response);
        }



        
    }




    
}
