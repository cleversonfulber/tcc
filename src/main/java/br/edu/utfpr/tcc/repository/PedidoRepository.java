package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
