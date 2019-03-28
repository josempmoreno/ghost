package es.josempmoreno.ghost.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.josempmoreno.ghost.dto.Word;
import es.josempmoreno.ghost.interfaces.WordRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GhostService {
	
	@Autowired
	WordRepository wordRepository;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	
	
//		a ver, ahora mismo tengo 3 requisitos del juego, que se sacan rápido del texto 
//		1.-Si introduces una letra que haga que no se pueda continuar se pierde, o sin sentido ejemplo “qq”
//		2.-gana el que genere una pálabra válida mayor que 3 letras
//		3.-Empieza el usuario
//
//		y ahora
//
//		0.- El usuario escoje modo fácil o modo díficil
//		1.-el usuario intruduce una letra 1
//		2.-la cpu busca todas las palabras que empiecen por esa letra generando una lista con el resultado, 
//		2.1.- SI es modo fácil devolverá la segunda letra de la palabra más larga
//		2.2.- Si es modo díficil devolvera la segunda letra de la palabra más corta
//		3.-El usuario introduce la letra 3
//		3.1.-Si existe esa palabra el usuario gana, si no existe,la cpu busca la palabra más corta/larga con esas tres letras,si hay una palabra de 4 letras con esa combinación la CPU gana, si no,devuelve la 4 letra de la palabra más corta/larga..
//		4.- El usuario introduce la letra N…
//		4.1.-Si existe esa palabra el usuario gana, si no existe,la cpu busca la palabra más corta/larga con esas N letras,si hay una palabra de N letras con esa combinación la CPU gana, si no,devuelve la N+1 letra de la palabra más corta/larga..

	public String findWord(String level,String letters) {
		
		
		List<Word> result = wordRepository.findByLetters(letters);
		log.info("size result for letters "+letters +" : "+ result.size());
//		return result.get(0).getValue();
		
		return "holita";
		//search all word 
		
	}

}
