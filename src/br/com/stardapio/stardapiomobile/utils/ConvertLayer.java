package br.com.stardapio.stardapiomobile.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.stardapio.stardapiomobile.bean.RestaurantAssociateCluster;

import com.stardapio.vos.RestaurantAssociate;

public class ConvertLayer {
	public static List<RestaurantAssociateCluster> convertToCluster(
			List<RestaurantAssociate> restaurants) {
		List<RestaurantAssociateCluster> aux = new ArrayList<RestaurantAssociateCluster>();
		for (RestaurantAssociate r : restaurants) {
			aux.add(new RestaurantAssociateCluster(r));
		}
		return aux;
	}
}
