package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "select * from pedido inner join enderecos on pedido.endereco_id = enderecos.id inner join usuarios on enderecos.usuario_id = usuarios.id where usuarios.id = ?1 order by pedido.id DESC", nativeQuery = true)
    List<Pedido> listarPedidos(Long id);
}
