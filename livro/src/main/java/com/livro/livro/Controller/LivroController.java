package com.livro.livro.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.livro.livro.Model.Livro;
import com.livro.livro.Repository.LivroRepository;

@Controller
@RequestMapping("/")
public class LivroController {

	private LivroRepository livroRepository;

	@Autowired
	public LivroController(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	@RequestMapping(value = "/{autor}", method = RequestMethod.GET)

	public String listalivros(@PathVariable("autor") String autor, Model model) {
		List<Livro> listaLivros = livroRepository.findByAutor(autor);
		if (listaLivros != null) {
			model.addAttribute("livros", listaLivros);

		}
		return "ListaLivro";
	}

	@RequestMapping(value = "/{autor}", method = RequestMethod.POST)
	public String adicionaLivroAutor(@PathVariable("autor") String autor, Livro livro) {
		livro.setAutor(autor);
		livroRepository.save(livro);
		return "redirect:/{autor}";
	}
}
