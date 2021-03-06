/*
 * $Id: BasePhonebookApplication.java 4003 2008-07-02 08:56:45Z funkattack $
 * $Revision: 4003 $
 * $Date: 2008-07-02 01:56:45 -0700 (Wed, 02 Jul 2008) $
 *
 * ==============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.bindgen.wicket.phonebook.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.bindgen.wicket.phonebook.web.page.ListContactsPage;
import org.springframework.context.ApplicationContext;


/**
 * @author Kare Nuorteva
 */
public abstract class BasePhonebookApplication extends WebApplication
{
	@Override
	public Class<? extends Page> getHomePage()
	{
		return ListContactsPage.class;
	}

	@Override
	protected void init()
	{
		super.init();
		addComponentInstantiationListener(new SpringComponentInjector(this, context()));
	}

	public abstract ApplicationContext context();
}
