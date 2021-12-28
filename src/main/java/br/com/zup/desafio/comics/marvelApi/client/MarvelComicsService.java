package br.com.zup.desafio.comics.marvelApi.client;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.zup.desafio.comics.marvelApi.response.ComicsResponse;
import br.com.zup.desafio.comics.marvelApi.response.ResultsResponse;

@Service
public class MarvelComicsService {
	
	@Autowired
	private MarvelComicsClient marvelClient;
	
	@Value("${marvel.public.key}")
	private String publicKey;

	@Value("${marvel.private.key}")
	private String privateKey;
	
	public ResultsResponse findComicsById(Integer id) {
		Long currentDate = new Date().getTime();
		ComicsResponse response = marvelClient.findComicsById(id, currentDate,publicKey , hash(currentDate)).getBody();
		return response.getResult();
	}
	
	public ComicsResponse findAll() {
		Long currentDate = new Date().getTime();
		return marvelClient.findAll(currentDate, publicKey, hash(currentDate));
		
	}

	private String hash(Long currentDate) {
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            var Stringhash = currentDate + privateKey + publicKey;
            BigInteger hash = new BigInteger(1, md.digest(Stringhash.getBytes(StandardCharsets.UTF_8)));
            return hash.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
	}

	
}
