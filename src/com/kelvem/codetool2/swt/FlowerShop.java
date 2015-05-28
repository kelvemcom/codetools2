package com.kelvem.codetool2.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This class demonstrates manipulation of inherited objects and manipulation of
 * publically-accessible components of included classes.
 * 
 * This class extends ShopFrame, which has several accessible inherited objects
 * (shopPanel, shopLabel, and the buttons at the bottom of the frame).
 * Properties can be set on these objects (eg, the border and layout manager of
 * the shopPanel) and elements can also be added to them (eg, FlowerPanels are
 * added to the shopPanel). Also, events can be listened to on inherited objects
 * (eg, the "Checkout" button's action event is listened to in this class).
 * 
 * The FlowerPanels which are added to the shopPanel can also be manipulated
 * (eg, the label's text can be changed) and methods can be called on them (eg,
 * calling getText on the quantityTextField).
 * 
*/
public class FlowerShop extends ShopFrame {
	private FlowerPanel flowerPanel1;

	private FlowerPanel flowerPanel2;

	private FlowerPanel flowerPanel3;

	private FlowerPanel flowerPanel4;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		showGUI();
	}

	public FlowerShop(Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		try {
			this.setSize(500, 300);
			super.shopPanel.setText("Flowers");
			this.jButton2.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					System.out.println("CHECKOUT "
							+ flowerPanel1.quantityTextField.getText()
							+ " Petunias.");
				}
			});

			GridLayout shopPanelLayout = new GridLayout();
			super.shopPanel.setLayout(shopPanelLayout);

			{
				flowerPanel1 = new FlowerPanel(super.shopPanel, SWT.NONE);
				flowerPanel1.setFlowerFamily(FlowerPanel.BANANA);
				GridData flowerPanel1LData = new GridData();
				flowerPanel1LData.grabExcessHorizontalSpace = true;
				flowerPanel1LData.horizontalAlignment = GridData.FILL;
				flowerPanel1LData.verticalAlignment = GridData.FILL;
				flowerPanel1LData.grabExcessVerticalSpace = true;
				flowerPanel1.setLayoutData(flowerPanel1LData);
			}
			{
				flowerPanel2 = new FlowerPanel(super.shopPanel, SWT.NONE);
				flowerPanel2.setFlowerFamily(FlowerPanel.SUNFLOWER);
				GridData flowerPanel2LData = new GridData();
				flowerPanel2LData.grabExcessHorizontalSpace = true;
				flowerPanel2LData.horizontalAlignment = GridData.FILL;
				flowerPanel2LData.verticalAlignment = GridData.FILL;
				flowerPanel2LData.grabExcessVerticalSpace = true;
				flowerPanel2.setLayoutData(flowerPanel2LData);
			}
			{
				flowerPanel4 = new FlowerPanel(super.shopPanel, SWT.NONE);
				flowerPanel4.setFlowerFamily(FlowerPanel.CLEMATIS);
				GridData flowerPanel4LData = new GridData();
				flowerPanel4LData.grabExcessHorizontalSpace = true;
				flowerPanel4LData.horizontalAlignment = GridData.FILL;
				flowerPanel4LData.verticalAlignment = GridData.FILL;
				flowerPanel4LData.grabExcessVerticalSpace = true;
				flowerPanel4.setLayoutData(flowerPanel4LData);
			}
			{
				flowerPanel3 = new FlowerPanel(super.shopPanel, SWT.NONE);
				flowerPanel3.setFlowerFamily(FlowerPanel.HOLLYHOCK);
				GridData flowerPanel3LData = new GridData();
				flowerPanel3LData.grabExcessHorizontalSpace = true;
				flowerPanel3LData.horizontalAlignment = GridData.FILL;
				flowerPanel3LData.verticalAlignment = GridData.FILL;
				flowerPanel3LData.grabExcessVerticalSpace = true;
				flowerPanel3.setLayoutData(flowerPanel3LData);
			}
			flowerPanel1.quantityTextField.setText("0");
			flowerPanel1.getFlowerLabel().setText("Musa Basjoo");
			
			flowerPanel2.quantityTextField.setText("0");
			flowerPanel2.getFlowerLabel().setText("Autumn Gold");

			flowerPanel4.quantityTextField.setText("0");
			flowerPanel4.getFlowerLabel().setText("Spring Glory");

			flowerPanel3.quantityTextField.setText("0");
			flowerPanel3.getFlowerLabel().setText("English Blue");

			this.shopLabel.setText("The Flower Shop");
			layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* Auto-generated method to display this 
	* Composite inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		FlowerShop inst = new FlowerShop(shell, SWT.NULL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
