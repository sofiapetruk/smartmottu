package br.com.fiap.smartmottu.service;

import br.com.fiap.smartmottu.entity.Usuario;
import br.com.fiap.smartmottu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Cacheable(cacheNames = "motos", key = "#idUsuario")
    public List<Usuario> findAll(Sort idUsuario) {
        return usuarioRepository.findAll();
    }

    @Cacheable(cacheNames = "motos", key = "#idUsuario")
    public Optional<Usuario> findById(Long idUsuario) {
        return  usuarioRepository.findById(idUsuario);
    }

    public Page<Usuario> findAllPage(PageRequest request) {
        return usuarioRepository.findAll(request);
    }

    @CachePut(cacheNames = "usuarios", key = "#usuario.idUsuario")
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @CacheEvict(cacheNames = "usuarios", key = "#idUsuario")
    public void delete(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

}
