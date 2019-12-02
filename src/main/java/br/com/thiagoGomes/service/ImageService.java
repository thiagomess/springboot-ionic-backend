package br.com.thiagoGomes.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.thiagoGomes.service.exceptions.FileException;

/**
 * Classe responsavel por manipular a imagem para transforma-la em JPG.
 *
 */
@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {

		String extensao = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equals(extensao) && !"jpg".equals(extensao)) {
			throw new FileException("Somente imagens PNG e JPG são permitidas");
		}
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(extensao)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

//	Converte png para JPG
	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);

		return jpgImage;
	}

	// Devolve a imagem em formato inpuStream
	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	//Metodo que corta a imagem para ficar quadrada
	public BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		return Scalr.crop(
				sourceImg, 
				(sourceImg.getWidth() / 2) - (min / 2), 
				(sourceImg.getHeight() / 2) - (min / 2),
				min, min);
	}

	//metodo para redimessionar a imagem para o tamanho passado
	public BufferedImage resize(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}

}
