package Components;

import java.awt.Insets;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import Common.AggregationDao;
import Common.ClassDao;
import Common.DataApi;
import Common.Features;
import Common.RelationDao;
import Common.RelationType;
import Notifiers.DetailsNotifier;

public class DetailsTextField extends JTextArea implements Observer {
	Features features =new Features();
	
	public DetailsTextField(String title){
		super();
		this.setLineWrap(true);
		this.setBorder(new TitledBorder(title));
		
	}
	public DetailsTextField(String title,Features features){
		super();
		this.setLineWrap(true);
		this.setBorder(new TitledBorder(title));
		this.features=features;
	}
	
	public Features getFeatures(){
		return this.features;
	}
	public void setFeatures(Features features){
		this.features=features;
	}
	public void update(Observable o, Object arg) {
		//extraire la valeur 
		String selectedValue=((DetailsNotifier)o).getSelectedValue();
		String details="";
		if(selectedValue.contains(("R"))){
			//afficher les details de la relations
			String relationName=selectedValue.substring(selectedValue.indexOf(")")+1,selectedValue.length()).trim();
			RelationDao relation=DataApi.relations.get(relationName);
			if(relation!=null){
				details=relation.getRelationDetails();
			}
			
		}else if(selectedValue.contains("A")){
			//afficher les details de l'aggregation
			String containerClass=((DetailsNotifier)o).getClassContainerName();
			String selectedAggregationPart=((DetailsNotifier)o).getSelectedValue();
			String selectedPart=selectedAggregationPart.substring(selectedAggregationPart.indexOf("_")+1,selectedAggregationPart.length());
			AggregationDao selectedAggregation=DataApi.aggregations.get(containerClass) != null?DataApi.aggregations.get(containerClass):null;
			details=selectedAggregation.getAggregationDetails();
			
			
			
		}
		this.setText("");
		this.append(details);
		
	}
}
