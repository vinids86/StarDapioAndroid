package br.com.stardapio.stardapiomobile;
import java.io.IOException;
import java.util.List;

import com.stardapio.clientrest.ClientRest;
import com.stardapio.vos.CategoryVO;
import com.stardapio.vos.ProductVO;
import com.stardapio.vos.RestaurantAssociate;
import com.stardapio.vos.ResultRestaurants;
/*

public class Test {
	public static void main(String[] args) throws IOException {
		ClientRest cli = new ClientRest();
		ResultRestaurants result = cli.findCommercialPoints(0.0, 0.0);
		//tela1
		for(RestaurantAssociate a : result.getRestauranteAssociates()){
			ResultRestaurants result2 = cli.findCommercialPointsById(a.getId());
			//tela2
			for(RestaurantAssociate r2 : result2.getRestauranteAssociates()){
				System.out.println(r2.getName());
				System.out.println(r2.getPoint().getLatitude()+"  "+r2.getPoint().getLongitude());
			}
		}
		//tela 1
		for(RestaurantPoint a : result.getRestaurants()){
			ResultRestaurants result2 = cli.findCommercialPointsById(a.getId());
			//tela2
			for(RestaurantAssociate r2 : result2.getRestauranteAssociates()){
				System.out.println(r2.getName());
				System.out.println(r2.getPoint().getLatitude()+"  "+r2.getPoint().getLongitude());
			}
		}
		List<CategoryVO> categ = cli.findCategories(1);
		//tela 3
		for(CategoryVO c : categ)
			System.out.println(c.getName());
		List<ProductVO>produtos = cli.findProduct(1);
		//tela 4
		for(ProductVO p : produtos)
			System.out.println(p.getName());
	}
}
*/