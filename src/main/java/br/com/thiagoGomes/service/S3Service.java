package br.com.thiagoGomes.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename(); //pega o nome do arquivo
			InputStream is = multipartFile.getInputStream(); //processamento do arquivo
			String contentType = multipartFile.getContentType(); //tipo do arquivo
			long contentLength = multipartFile.getSize(); //tamanho do arquivo

			return uploadFile(is, fileName, contentType, contentLength);
		} catch (IOException e) {
			throw new RuntimeException("Erro de IO " + e.getMessage());
		}

	}

	public URI uploadFile(InputStream is, String fileName, String contentType, long contentLength) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			meta.setContentLength(contentLength);

			LOG.info("Iniciando upload");

			s3Client.putObject(new PutObjectRequest(bucketName, fileName, is, meta)); // faz o upload do arquivo

			LOG.info("Upload finalizado");

			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}
	}

}
