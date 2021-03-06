package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ActionListeners.ShowDetailsListener;
import Common.Features;
import Components.ClassesList;
import Components.DetailsTextField;
import Components.FileUploaderButton;
import Components.FileUploaderInput;
import Components.ScrollPane;
import Notifiers.AttributesNotifier;
import Notifiers.ClassNotifier;
import Notifiers.DetailsNotifier;
import Notifiers.InputFileNotifier;
import Notifiers.MethodeNotifier;
import Notifiers.RelationsNotifier;
import Notifiers.SubClassesNotifier;

public class MainController {

	private  PanelContoller panel;
	
	public MainController(PanelContoller panel){
		this.panel=panel;
		this.init();
	}
	
	private void init(){
		 //final PanelContoller panel=new PanelContoller(new GridBagLayout());
		 FileUploaderButton fileUploaderButton= new FileUploaderButton();
		 FileUploaderInput fileUploaderInput = new FileUploaderInput("entrer un fichier ");
		 DetailsTextField detailsField=new DetailsTextField("Details");
		 DefaultListModel listModel = new DefaultListModel();
		 ClassesList classes=new ClassesList(listModel, "classes");
		 JPanel attrPanel = new JPanel();
		 JPanel methodesPanel = new JPanel();
		 JPanel subClassesPanel = new JPanel();
		 JPanel aggrAsoPanel = new JPanel();
		 
		 
		 
		 
		 
		 //ajout des caracteristiques de chaque composante ....
		 
		 //caracteristiques du button 
		 Features fileUploaderButtonFeatures=new Features(0,0,1,1,GridBagConstraints.RELATIVE,GridBagConstraints.CENTER,
				 new Insets(0,0,0,0),0,0,0,0);
		 
		 //caracteristiques de l'input
		 Features fileUploaderInputFeatures=new Features(1,0,3,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,
				 new Insets(0,15,0,0),0,10,1,0);
		 
		 //caracteristiques du container des classes
		 Features classListFeatures=new Features(0,1,2,3,GridBagConstraints.VERTICAL,GridBagConstraints.CENTER,
				 new Insets(17,0,0,15),-205,0,0,0);
		 
		 //caracteristiques des quatre scrollPanel su milieu
		 
		 Features componentOne =new Features(2,1,1,1,GridBagConstraints.RELATIVE,GridBagConstraints.CENTER,
				 new Insets(15, 0, 15, 15),0,0,0,0);
		 
		 Features componentTwo =new Features(3,1,1,1,GridBagConstraints.RELATIVE,GridBagConstraints.CENTER,
				 new Insets(15, 0, 15, 0),0,0,0,0);
		 
		 
		 Features componentThree =new Features(2,2,1,1,GridBagConstraints.RELATIVE,GridBagConstraints.CENTER,
				 new Insets(0, 0, 15, 15),0,0,0,0);

		 Features componentFour =new Features(3,2,1,1,GridBagConstraints.RELATIVE,GridBagConstraints.CENTER,
				 new Insets(0, 0, 15, 0),0,0,0,0);
		 
		 //caracterisriques du textField  des details
		 Features DetailsFeatures =new Features(2,3,2,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,
				 new Insets(0, 0, 15, 0),0,0,1,0);
		 
		 
		 //l'ajout des caracteristiques de chaque composante
		 fileUploaderButton.setComponentFeatures(fileUploaderButtonFeatures);
		 fileUploaderInput.setComponentFeatures(fileUploaderInputFeatures);
		 classes.setFeatures(classListFeatures);
		 detailsField.setFeatures(DetailsFeatures);
		 
		 //instanciation des quatres composante du milieu 
		 ScrollPane scrollPaneOne=new ScrollPane("Attributs",componentOne);	
		 ScrollPane scrollPaneTwo=new ScrollPane("Methodes",componentTwo);
		 ScrollPane scrollPaneThree=new ScrollPane("Sous Classes",componentThree);
		 ScrollPane scrollPaneFour=new ScrollPane("Associations/Aggregations",componentFour);
		 
		 
		 //ajout de composantes dans chaque scrollPane
		 scrollPaneOne.setComponentInScrollPane(new JTextArea());
		 scrollPaneTwo.setComponentInScrollPane(new JTextArea());
		 scrollPaneThree.setComponentInScrollPane(new JTextArea());
		 JList<String> innerList=new JList<String>(new DefaultListModel());
		 ShowDetailsListener showDetailsListener=new ShowDetailsListener();
		 innerList.addListSelectionListener(showDetailsListener);
		 scrollPaneFour.setComponentInScrollPane(innerList);
		 
		 //initialiser les dimentions de chaque composante
		 fileUploaderInput.setPreferredSize(new Dimension(250,15));
		 scrollPaneOne.setPreferredSize(new Dimension(220,220));
		 scrollPaneTwo.setPreferredSize(new Dimension(220,220));
		 scrollPaneThree.setPreferredSize(new Dimension(220,220));
		 scrollPaneFour.setPreferredSize(new Dimension(220,220)); 
		 detailsField.setPreferredSize(new Dimension(300,150));
		 
		 //l'ajout des composantes au panel
		 panel.addComponent(fileUploaderButton, fileUploaderButton.getComponentFeatures());
		 panel.addComponent(fileUploaderInput, fileUploaderInput.getComponentFeatures());
		 panel.addComponent(classes, classes.getFeatures());
		 panel.addComponent(scrollPaneOne,scrollPaneOne.getFeatures() );
		 panel.addComponent(scrollPaneTwo,scrollPaneTwo.getFeatures() );
		 panel.addComponent(scrollPaneThree,scrollPaneThree.getFeatures() );
		 panel.addComponent(scrollPaneFour,scrollPaneFour.getFeatures() );
		 panel.addComponent(detailsField, detailsField.getFeatures());
		 
		 //creation des observateurs
		 ClassNotifier classNotifier=new ClassNotifier();
		 AttributesNotifier attributesNotifier=new AttributesNotifier();
		 MethodeNotifier methodesNotifier=new MethodeNotifier();
		 SubClassesNotifier subClassesNotifier=new SubClassesNotifier();
		 RelationsNotifier relationsNotifier=new RelationsNotifier();
		 DetailsNotifier detailsNotifier=new DetailsNotifier();
		 InputFileNotifier inputFileNotifier=new InputFileNotifier();
		 
		 //l'ajout des observateurs de chaque composante 
		 fileUploaderButton.setClassNotifier(classNotifier);
		 classes.setClassNotifier(classNotifier);
		 classNotifier.addObserver(classes);
		 classes.setAttributesNotifier(attributesNotifier);
		 classes.setMethodesNotifier(methodesNotifier);
		 attributesNotifier.addObserver(scrollPaneOne);
		 methodesNotifier.addObserver(scrollPaneTwo);
		 classes.setSubClassNotifier(subClassesNotifier);
		 subClassesNotifier.addObserver(scrollPaneThree);
		 classes.setRelationsNotifier(relationsNotifier);
		 relationsNotifier.addObserver(scrollPaneFour);
		 classes.setDetailsNotifier(detailsNotifier);
		 showDetailsListener.setDetailsNotifier(detailsNotifier);
		 detailsNotifier.addObserver(detailsField);
		 fileUploaderButton.setInputFileNotifier(inputFileNotifier);
		 inputFileNotifier.addObserver(fileUploaderInput);
		 
	}

	public PanelContoller getPanel() {
		return panel;
	}

	public void setPanel(PanelContoller panel) {
		this.panel = panel;
	}

	

}
