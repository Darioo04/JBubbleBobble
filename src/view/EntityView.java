package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import model.Entity;

@SuppressWarnings("deprecation")
public class EntityView extends JLabel implements Observer {
	
	private Entity entity;
	
	public EntityView(Entity entity) {
        this.entity = entity;
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
