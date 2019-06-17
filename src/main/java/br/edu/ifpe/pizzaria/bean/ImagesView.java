package br.edu.ifpe.pizzaria.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ImagesView {

	private List<String> images;
	private List<String> imgsCard;

	@PostConstruct
	public void init() {
		images = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			images.add("pizza" + i + ".jpg");
		}
		
		imgsCard = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			imgsCard.add("cardapio" + i + ".jpg");
		}
		
	}

	public List<String> getImages() {
		return images;
	}

	public List<String> getImgsCard() {
		return imgsCard;
	}
		
}
