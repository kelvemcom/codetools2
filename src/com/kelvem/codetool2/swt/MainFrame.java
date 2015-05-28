/**
 * 
 */
package com.kelvem.codetool2.swt;
import org.eclipse.swt.*;

import org.eclipse.swt.widgets.*;

import org.eclipse.swt.layout.*;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MainFrame {
	static private Composite composite1;

    public static void main(String[] args) {

        Display display = new Display(); //创建Display实例

        Shell shell = new Shell(display);  //创建Shell实例

        shell.setText("窗口");              //设置窗口的显示标签

        shell.setBounds(200,200,500,350);    //设置shell的显示范围

        //设置窗口布局

        FillLayout layout = new FillLayout(org.eclipse.swt.SWT.VERTICAL);

        shell.setLayout(layout);
        {
        	composite1 = new Composite(shell, SWT.NONE);
        	RowLayout composite1Layout = new RowLayout(org.eclipse.swt.SWT.HORIZONTAL);
        	composite1.setLayout(composite1Layout);
        	composite1.setSize(200, 200);

            Label n1 = new Label(composite1,SWT.CENTER);
            RowData n1LData = new RowData();
            n1.setLayoutData(n1LData);
            n1.setText("N1");
            Text t1 = new Text(composite1, SWT.CENTER);
            RowData t1LData = new RowData();
            t1.setLayoutData(t1LData);
            t1.setText("T1");
        }

        //创建标签，用于显示"你好，SWT！"字样

//        Label helloLabel=new Label(shell,SWT.CENTER);//采用SWT.CENTER样式，即居中显示
//        helloLabel.setText("你好，SWT！");

        
        shell.pack(); //以紧凑方式显示窗口并自动调节大小

        shell.open(); //打开shell，类似于打开窗口

        //开始事件处理循环，直到用户关闭窗口

        while (!shell.isDisposed()) {

        if (!display.readAndDispatch())

             display.sleep();

        }

        display.dispose();

        }

    }


