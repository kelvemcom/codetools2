package com.kelvem.codetool2.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class FlowerPanel extends Composite {
	private CLabel jLabel1;

	private CLabel flowerLabel;

	private CLabel jLabel2;

	private CLabel jLabel3;

	public Text quantityTextField;
	
	public static final int UNDEFINED = 0;
	public static final int SUNFLOWER = 1;
	public static final int BANANA = 2;
	public static final int HOLLYHOCK = 3;
	public static final int CLEMATIS = 4;
	
	private int flowerFamily = UNDEFINED;

	public FlowerPanel(Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		try {
			RowLayout thisLayout = new RowLayout(org.eclipse.swt.SWT.HORIZONTAL);
			thisLayout.pack = false;
			thisLayout.justify = true;
			thisLayout.fill = true;
			this.setLayout(thisLayout);

			jLabel1 = new CLabel(this, SWT.NONE);
			jLabel1.setText("Flower:");

			jLabel2 = new CLabel(this, SWT.NONE);
			jLabel2.setText("(Family)");

			flowerLabel = new CLabel(this, SWT.NONE);
			flowerLabel.setText("$NAME$");

			jLabel3 = new CLabel(this, SWT.NONE);
			jLabel3.setText("Quantity:");

			quantityTextField = new Text(this, SWT.NONE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return Returns the flowerLabel.
	 */
	public CLabel getFlowerLabel() {
		return flowerLabel;
	}

	public int getFlowerFamily() {
		return flowerFamily;
	}

	public void setFlowerFamily(int flowerFamily) {
		this.flowerFamily = flowerFamily;
		switch (flowerFamily) {
		case UNDEFINED:
			jLabel2.setText("( none )");
			break;
		case BANANA:
			jLabel2.setText("( banana )");
			break;
		case SUNFLOWER:
			jLabel2.setText("( sunflower )");
			break;
		case HOLLYHOCK:
			jLabel2.setText("( hollyhock )");
			break;
		case CLEMATIS:
			jLabel2.setText("( clematis )");
			break;
		}
	}
}
