package com.h2.caralho.h2caralho;

import com.h2.caralho.h2caralho.domain.*;
import com.h2.caralho.h2caralho.domain.enums.EstadoPagamento;
import com.h2.caralho.h2caralho.domain.enums.Perfil;
import com.h2.caralho.h2caralho.domain.enums.TipoCliente;
import com.h2.caralho.h2caralho.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.text.SimpleDateFormat;
import java.util.Arrays;
@EnableAuthorizationServer
@SpringBootApplication
public class H2caralhoApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PasswordEncoder pe;

    public static void main(String[] args) {
        SpringApplication.run(H2caralhoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);


        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Bruno", "bruno@moreno.com", "123", TipoCliente.PESSOAFISICA, pe.encode("sasasa123"));
        cli1.addPerfil(Perfil.ADMIN);
        cli1.getTelefones().addAll(Arrays.asList("123456", "789101"));
        Endereco e1 = new Endereco(null, "Logradouro", "123", "", "Bairro", "17015031", cli1, c3);
        Endereco e2 = new Endereco(null, "Logradouro", "12356", "", "Bairro", "17015031", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("29/04/2017 22:21"), cli1, e2);

        Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 10);
        Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDETE, ped2, sdf.parse("01/05/2017 00:00"), sdf.parse("30/01/2017 00:00"));
        ped1.setPagamento(pag1);
        ped2.setPagamento(pag2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 20.00, 1, 10.00);
        ItemPedido ip2 = new ItemPedido(ped1, p2, 300.00, 2, 50.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 800.00, 1, 100.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip2, ip3));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

    }
}
