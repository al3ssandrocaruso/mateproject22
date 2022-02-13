/*
 *    Copyright (C) 2021 Guglielmo De Angelis (a.k.a. Gulyx)
 *    
 *    This file is part of the contents developed for the course
 * 	  ISPW (A.Y. 2021-2022) at Università di Tor Vergata in Rome. 
 *
 *    This is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as 
 *    published by the Free Software Foundation, either version 3 of the 
 *    License, or (at your option) any later version.
 *
 *    This software is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with this source.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package kapta.utils.decorations;
import javafx.scene.layout.VBox;
import kapta.utils.VisualComponent;

public abstract class Decorator implements VisualComponent {

	private VisualComponent component;
	
	protected Decorator(VisualComponent component){
		this.component = component;
	}

	@Override
	public VBox addUserPanel(){
		return this.component.addUserPanel();
	}

}
