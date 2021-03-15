package com.h2.caralho.h2caralho.services;

import com.h2.caralho.h2caralho.domain.Cliente;
import com.h2.caralho.h2caralho.repositories.ClienteRepository;
import com.h2.caralho.h2caralho.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(s);
        if(cliente == null){
            throw new UsernameNotFoundException(s);
        }
        return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
    }
}
