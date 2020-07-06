package com.trinity.peoplemanagement.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.trinity.peoplemanagement.domain.exception.CPFEmUsoException;
import com.trinity.peoplemanagement.domain.exception.EntidadeEmUsoException;
import com.trinity.peoplemanagement.domain.exception.EntidadeNaoEncontradaException;
import com.trinity.peoplemanagement.domain.model.Pessoa;
import com.trinity.peoplemanagement.domain.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa salvar(Pessoa pessoa) {

		try {
			String cpfSemMascara = pessoa.getCpf().replaceAll("[.-]", "");
			
			if (pessoa.getEndereco() != null) {
				String cepSemMascara = pessoa.getEndereco().getCep().replaceAll("[-]", "");
				pessoa.getEndereco().setCep(cepSemMascara);
			}

			pessoa.setCpf(cpfSemMascara);
			Pessoa pessoaSalva = pessoaRepository.save(pessoa);

			return pessoaSalva;
		} catch (DataIntegrityViolationException e) {
			throw new CPFEmUsoException("Database error " + e.getMessage());
		}

	}

	public void excluir(Long id) {
		try {
			pessoaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não eciste um cadastro de cidade com o código %d", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de código %d, não pode ser removida, pois está sendo usada.", id));
		}
	}

}
