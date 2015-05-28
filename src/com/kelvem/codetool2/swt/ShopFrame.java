package com.kelvem.codetool2.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class ShopFrame extends org.eclipse.swt.widgets.Composite {
	public CLabel shopLabel;
	public Button jButton4;
	public Button jButton3;
	public Button jButton2;
	public Button jButton1;
	public Group shopPanel;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		showGUI();
	}
	
	public ShopFrame(Composite parent, int style) {
		super(parent, style);
		initGUI();
	}
	
	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout(4, true);
			thisLayout.numColumns = 4;

			this.setLayout(thisLayout);
			this.setSize(374, 256);

			{
				shopLabel = new CLabel(this, SWT.NONE);
				shopLabel.setText("$SHOP$");
				shopLabel.setLayout(null);
				GridData shopLabelLData = new GridData();
				shopLabel.setAlignment(SWT.CENTER);
				shopLabelLData.heightHint = 19;
				shopLabelLData.horizontalSpan = 4;
				shopLabelLData.grabExcessHorizontalSpace = true;
				shopLabelLData.horizontalAlignment = GridData.FILL;
				shopLabel.setLayoutData(shopLabelLData);
			}
			{
				shopPanel = new Group(this, SWT.NONE);
				RowLayout shopPanelLayout = new RowLayout(org.eclipse.swt.SWT.HORIZONTAL);
				GridData shopPanelLData = new GridData();
				shopPanelLData.horizontalSpan = 4;
				shopPanelLData.verticalAlignment = GridData.FILL;
				shopPanelLData.horizontalAlignment = GridData.FILL;
				shopPanelLData.grabExcessHorizontalSpace = true;
				shopPanelLData.grabExcessVerticalSpace = true;
				shopPanel.setLayoutData(shopPanelLData);
				shopPanel.setLayout(shopPanelLayout);
				shopPanel.setText("$Items$");
			}
			{
				jButton1 = new Button(this, SWT.NONE);
				jButton1.setText("Cancel");
				GridData jButton1LData = new GridData();
				jButton1LData.widthHint = 79;
				jButton1LData.heightHint = 22;
				jButton1.setLayoutData(jButton1LData);
			}
			{
				jButton2 = new Button(this, SWT.NONE);
				jButton2.setText("Checkout");
				GridData jButton2LData = new GridData();
				jButton2LData.widthHint = 79;
				jButton2LData.heightHint = 22;
				jButton2.setLayoutData(jButton2LData);
			}
			{
				jButton3 = new Button(this, SWT.NONE);
				jButton3.setText("Add...");
				GridData jButton3LData = new GridData();
				jButton3LData.widthHint = 79;
				jButton3LData.heightHint = 22;
				jButton3.setLayoutData(jButton3LData);
			}
			{
				jButton4 = new Button(this, SWT.NONE);
				jButton4.setText("Remove");
				GridData jButton4LData = new GridData();
				jButton4LData.widthHint = 79;
				jButton4LData.heightHint = 22;
				jButton4.setLayoutData(jButton4LData);
			}

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
		ShopFrame inst = new ShopFrame(shell, SWT.NULL);
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
