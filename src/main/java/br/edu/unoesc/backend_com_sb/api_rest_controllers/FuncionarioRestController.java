package br.edu.unoesc.backend_com_sb.api_rest_controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.backend_com_sb.model.Funcionario;

@RestController
@RequestMapping(value = "/api")
public class FuncionarioRestController {
	
	Funcionario f1 = new Funcionario(1L, "Pedro", 10, new BigDecimal("1300.00"));
	Funcionario f2 = new Funcionario(2L, "Ana", 20, new BigDecimal("2500.00"));
	List<Funcionario> funcionarios;
	
	public FuncionarioRestController() {
		funcionarios = new ArrayList<Funcionario>();
		
		funcionarios.add(f1);
		funcionarios.add(f2);
	}
	
	// Incluir produto
	@PostMapping("/funcionarios")
	public Funcionario salvarProduto(@RequestBody Funcionario funcionario) {
		System.out.println("Inserindo um novo funcionario...");
		System.out.println(funcionario);
		
		funcionarios.add(funcionario);
		
		System.out.println(this.listarFuncionarios());
		
		return funcionario;
	}
	
	// Alterar produto
	@PutMapping("/funcionarios")
	public Funcionario atualizarFuncionario(@RequestBody Funcionario funcionario) {
		Funcionario f = findById(funcionario.getId());
		System.out.println(f);
		
		f.setNome(funcionario.getNome());
		f.setNumeroDependentes(funcionario.getNumeroDependentes());
		f.setSalario(funcionario.getSalario());
		
		System.out.println("Atualizando o funcionario...");
		System.out.println(f);
		
		System.out.println(this.listarFuncionarios());
		
		return f;
	}
	
	// Excluir produto
	@DeleteMapping(value = "/funcionarios/{id}")
	public void deletarFuncionario(@PathVariable Long id) {
		Funcionario f = findById(id);
		System.out.println(f);
		
		funcionarios.remove(f);
		
		System.out.println("Excluindo funcionario [" + id + "]...");
		
		System.out.println(this.listarFuncionarios());
	}
	
	@GetMapping(value = "/funcionarios")
	public List<Funcionario> listarFuncionarios() {
		return funcionarios;
	}
	
	@GetMapping(value = "/funcionarios/{id}")
	public Funcionario findById(@PathVariable Long id) {
		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getId().equals(id)) {
				return funcionario;
			}
		}
		
		return null;
	}
}