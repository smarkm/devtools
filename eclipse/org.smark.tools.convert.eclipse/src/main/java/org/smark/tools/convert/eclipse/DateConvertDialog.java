package org.smark.tools.convert.eclipse;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DateConvertDialog extends TitleAreaDialog {
	static String[] pattents = {"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyy/MM/dd HH:mm:ss"};  
	private Text txtDate;
	private Text txtMills;
	private Text txtD2M;
	private Text txtM2D;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DateConvertDialog(Shell parentShell) {
		super(parentShell);
		setTitle("Date Convert Tool");
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Date convert(yyyy-MM-dd HH:mm:ss | yyyyMMddHHmmss | yyyy/MM/dd HH:mm:ss  )\r\n\r\n\t\r\n\t\r\n\t");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(4, false));
		GridData gd_container = new GridData(GridData.FILL_BOTH);
		gd_container.verticalAlignment = SWT.TOP;
		container.setLayoutData(gd_container);
		
		Label lblDate = new Label(container, SWT.NONE);
		lblDate.setAlignment(SWT.RIGHT);
		lblDate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDate.setText("Date: ");
		
		txtDate = new Text(container, SWT.BORDER);
		txtDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("->");
		
		txtD2M = new Text(container, SWT.BORDER);
		txtD2M.setEditable(false);
		txtD2M.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		
		Label lblMills = new Label(container, SWT.NONE);
		lblMills.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMills.setText("Mills: ");
		
		txtMills = new Text(container, SWT.BORDER);
		txtMills.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("->");
		
		txtM2D = new Text(container, SWT.BORDER);
		txtM2D.setEditable(false);
		txtM2D.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		txtDate.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent event) {
				String dateStr = txtDate.getText();
				if (dateStr.length()==0) {
					txtD2M.setText("");
				}
				Date date = convertDate(dateStr);
				if (date!=null) {
					txtD2M.setText(date.getTime()+"");
				}
			}
		});
		
		txtMills.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent event) {
				String dateStr = txtMills.getText();
				if (dateStr.length()==0) {
					txtM2D.setText("");
				}
				try {
					if (dateStr.length()>0) {
						SimpleDateFormat format  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
						String rs = format.format(mills2Date(dateStr));
						txtM2D.setText(rs);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});

		return area;
	}
	public static Date mills2Date(String dateStr){
		Date date = null;
		date = new Date(Long.parseLong(dateStr));
		return date;
	}
	
	public static Date convertDate(String dateStr){
		Date date = null;
		for (String pattern : pattents) {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			try {
				date = format.parse(dateStr);
				break;
			} catch (ParseException e) {
			}
		}
		return date;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

}
