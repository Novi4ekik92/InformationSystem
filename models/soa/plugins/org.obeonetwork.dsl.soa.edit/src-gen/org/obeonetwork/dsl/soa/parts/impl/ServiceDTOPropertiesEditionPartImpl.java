/*******************************************************************************
 * Copyright (c) 2008-2009 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.dsl.soa.parts.impl;

// Start of user code for imports
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;

import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.ButtonsModeEnum;
import org.eclipse.emf.eef.runtime.ui.widgets.EObjectFlatComboViewer;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;

import org.eclipse.emf.eef.runtime.ui.widgets.eobjflatcombo.EObjectFlatComboSettings;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart;
import org.obeonetwork.dsl.soa.parts.SoaViewsRepository;

import org.obeonetwork.dsl.soa.providers.SoaMessages;

// End of user code

/**
 * @author <a href="mailto:jerome.benois@obeo.fr>J�r�me Benois</a>
 * 
 */
public class ServiceDTOPropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, ServiceDTOPropertiesEditionPart {

	protected Text name;
	protected EObjectFlatComboViewer supertype;
	protected ReferencesTable associatedTypes;
	protected List<ViewerFilter> associatedTypesBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> associatedTypesFilters = new ArrayList<ViewerFilter>();
	protected Text description;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public ServiceDTOPropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createFigure(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public Composite createFigure(final Composite parent) {
		view = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		view.setLayout(layout);
		createControls(view);
		return view;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createControls(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public void createControls(Composite view) { 
		CompositionSequence serviceDTOStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = serviceDTOStep.addStep(SoaViewsRepository.ServiceDTO.Properties.class);
		propertiesStep.addStep(SoaViewsRepository.ServiceDTO.Properties.name);
		propertiesStep.addStep(SoaViewsRepository.ServiceDTO.Properties.supertype);
		propertiesStep.addStep(SoaViewsRepository.ServiceDTO.Properties.associatedTypes);
		propertiesStep.addStep(SoaViewsRepository.ServiceDTO.Properties.description);
		
		
		composer = new PartComposer(serviceDTOStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == SoaViewsRepository.ServiceDTO.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == SoaViewsRepository.ServiceDTO.Properties.name) {
					return createNameText(parent);
				}
				if (key == SoaViewsRepository.ServiceDTO.Properties.supertype) {
					return createSupertypeFlatComboViewer(parent);
				}
				if (key == SoaViewsRepository.ServiceDTO.Properties.associatedTypes) {
					return createAssociatedTypesAdvancedReferencesTable(parent);
				}
				if (key == SoaViewsRepository.ServiceDTO.Properties.description) {
					return createDescriptionTextarea(parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	/**
	 * 
	 */
	protected Composite createPropertiesGroup(Composite parent) {
		Group propertiesGroup = new Group(parent, SWT.NONE);
		propertiesGroup.setText(SoaMessages.ServiceDTOPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	
	protected Composite createNameText(Composite parent) {
		createDescription(parent, SoaViewsRepository.ServiceDTO.Properties.name, SoaMessages.ServiceDTOPropertiesEditionPart_NameLabel);
		name = new Text(parent, SWT.BORDER);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ServiceDTOPropertiesEditionPartImpl.this, SoaViewsRepository.ServiceDTO.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
			}

		});
		name.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ServiceDTOPropertiesEditionPartImpl.this, SoaViewsRepository.ServiceDTO.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, SoaViewsRepository.ServiceDTO.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(SoaViewsRepository.ServiceDTO.Properties.name, SoaViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * @param parent the parent composite
	 * 
	 */
	protected Composite createSupertypeFlatComboViewer(Composite parent) {
		createDescription(parent, SoaViewsRepository.ServiceDTO.Properties.supertype, SoaMessages.ServiceDTOPropertiesEditionPart_SupertypeLabel);
		supertype = new EObjectFlatComboViewer(parent, !propertiesEditionComponent.isRequired(SoaViewsRepository.ServiceDTO.Properties.supertype, SoaViewsRepository.SWT_KIND));
		supertype.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		supertype.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ServiceDTOPropertiesEditionPartImpl.this, SoaViewsRepository.ServiceDTO.Properties.supertype, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SET, null, getSupertype()));
			}

		});
		GridData supertypeData = new GridData(GridData.FILL_HORIZONTAL);
		supertype.setLayoutData(supertypeData);
		supertype.setID(SoaViewsRepository.ServiceDTO.Properties.supertype);
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(SoaViewsRepository.ServiceDTO.Properties.supertype, SoaViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createAssociatedTypesAdvancedReferencesTable(Composite parent) {
		String label = getDescription(SoaViewsRepository.ServiceDTO.Properties.associatedTypes, SoaMessages.ServiceDTOPropertiesEditionPart_AssociatedTypesLabel);		 
		this.associatedTypes = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addAssociatedTypes(); }
			public void handleEdit(EObject element) { editAssociatedTypes(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveAssociatedTypes(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromAssociatedTypes(element); }
			public void navigateTo(EObject element) { }
		});
		this.associatedTypes.setHelpText(propertiesEditionComponent.getHelpContent(SoaViewsRepository.ServiceDTO.Properties.associatedTypes, SoaViewsRepository.SWT_KIND));
		this.associatedTypes.createControls(parent);
		this.associatedTypes.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ServiceDTOPropertiesEditionPartImpl.this, SoaViewsRepository.ServiceDTO.Properties.associatedTypes, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData associatedTypesData = new GridData(GridData.FILL_HORIZONTAL);
		associatedTypesData.horizontalSpan = 3;
		this.associatedTypes.setLayoutData(associatedTypesData);
		this.associatedTypes.disableMove();
		associatedTypes.setID(SoaViewsRepository.ServiceDTO.Properties.associatedTypes);
		associatedTypes.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addAssociatedTypes() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(associatedTypes.getInput(), associatedTypesFilters, associatedTypesBusinessFilters,
		"associatedTypes", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ServiceDTOPropertiesEditionPartImpl.this, SoaViewsRepository.ServiceDTO.Properties.associatedTypes,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				associatedTypes.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveAssociatedTypes(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ServiceDTOPropertiesEditionPartImpl.this, SoaViewsRepository.ServiceDTO.Properties.associatedTypes, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		associatedTypes.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromAssociatedTypes(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ServiceDTOPropertiesEditionPartImpl.this, SoaViewsRepository.ServiceDTO.Properties.associatedTypes, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		associatedTypes.refresh();
	}

	/**
	 * 
	 */
	protected void editAssociatedTypes(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				associatedTypes.refresh();
			}
		}
	}

	
	protected Composite createDescriptionTextarea(Composite parent) {
		Label descriptionLabel = createDescription(parent, SoaViewsRepository.ServiceDTO.Properties.description, SoaMessages.ServiceDTOPropertiesEditionPart_DescriptionLabel);
		GridData descriptionLabelData = new GridData(GridData.FILL_HORIZONTAL);
		descriptionLabelData.horizontalSpan = 3;
		descriptionLabel.setLayoutData(descriptionLabelData);
		description = new Text(parent, SWT.BORDER | SWT.WRAP | SWT.MULTI | SWT.V_SCROLL);
		GridData descriptionData = new GridData(GridData.FILL_HORIZONTAL);
		descriptionData.horizontalSpan = 2;
		descriptionData.heightHint = 80;
		descriptionData.widthHint = 200;
		description.setLayoutData(descriptionData);
		description.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(ServiceDTOPropertiesEditionPartImpl.this, SoaViewsRepository.ServiceDTO.Properties.description, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, description.getText()));
			}

		});
		EditingUtils.setID(description, SoaViewsRepository.ServiceDTO.Properties.description);
		EditingUtils.setEEFtype(description, "eef::Textarea"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(SoaViewsRepository.ServiceDTO.Properties.description, SoaViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		return parent;
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		// Start of user code 
		
		// End of user code
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#setName(String newValue)
	 * 
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#getSupertype()
	 * 
	 */
	public EObject getSupertype() {
		if (supertype.getSelection() instanceof StructuredSelection) {
			Object firstElement = ((StructuredSelection) supertype.getSelection()).getFirstElement();
			if (firstElement instanceof EObject)
				return (EObject) firstElement;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#initSupertype(EObjectFlatComboSettings)
	 */
	public void initSupertype(EObjectFlatComboSettings settings) {
		supertype.setInput(settings);
		if (current != null) {
			supertype.setSelection(new StructuredSelection(settings.getValue()));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#setSupertype(EObject newValue)
	 * 
	 */
	public void setSupertype(EObject newValue) {
		if (newValue != null) {
			supertype.setSelection(new StructuredSelection(newValue));
		} else {
			supertype.setSelection(new StructuredSelection()); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#setSupertypeButtonMode(ButtonsModeEnum newValue)
	 */
	public void setSupertypeButtonMode(ButtonsModeEnum newValue) {
		supertype.setButtonMode(newValue);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#addFilterSupertype(ViewerFilter filter)
	 * 
	 */
	public void addFilterToSupertype(ViewerFilter filter) {
		supertype.addFilter(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#addBusinessFilterSupertype(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToSupertype(ViewerFilter filter) {
		supertype.addBusinessRuleFilter(filter);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#initAssociatedTypes(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initAssociatedTypes(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		associatedTypes.setContentProvider(contentProvider);
		associatedTypes.setInput(settings);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#updateAssociatedTypes()
	 * 
	 */
	public void updateAssociatedTypes() {
	associatedTypes.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#addFilterAssociatedTypes(ViewerFilter filter)
	 * 
	 */
	public void addFilterToAssociatedTypes(ViewerFilter filter) {
		associatedTypesFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#addBusinessFilterAssociatedTypes(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToAssociatedTypes(ViewerFilter filter) {
		associatedTypesBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#isContainedInAssociatedTypesTable(EObject element)
	 * 
	 */
	public boolean isContainedInAssociatedTypesTable(EObject element) {
		return ((ReferencesTableSettings)associatedTypes.getInput()).contains(element);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#getDescription()
	 * 
	 */
	public String getDescription() {
		return description.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.obeonetwork.dsl.soa.parts.ServiceDTOPropertiesEditionPart#setDescription(String newValue)
	 * 
	 */
	public void setDescription(String newValue) {
		if (newValue != null) {
			description.setText(newValue);
		} else {
			description.setText(""); //$NON-NLS-1$
		}
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return SoaMessages.ServiceDTO_Part_Title;
	}

	// Start of user code 
	
	// End of user code


}
