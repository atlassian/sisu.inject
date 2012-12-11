/*******************************************************************************
 * Copyright (c) 2010, 2012 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stuart McCulloch (Sonatype, Inc.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.sisu.binders;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.spi.Element;
import com.google.inject.spi.Elements;
import org.eclipse.sisu.converters.FileTypeConverter;
import org.eclipse.sisu.converters.URLTypeConverter;
import org.eclipse.sisu.locators.BeanLocator;
import org.eclipse.sisu.scanners.analyzer.WiringFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Guice {@link Module} that automatically adds {@link BeanLocator}-backed bindings for non-local bean dependencies.
 */
public class WireModule
    implements Module
{
    // ----------------------------------------------------------------------
    // Implementation fields
    // ----------------------------------------------------------------------

    private final Iterable<WiringFactory> extensionWiringFactories;
    private final List<Module> modules;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public WireModule(Module... modules)
    {
        this(Arrays.asList(modules));
    }

    public WireModule(List<Module> modules)
    {
        this(modules, Collections.<WiringFactory>emptyList());
    }
    public WireModule(List<Module> modules, Iterable<WiringFactory> extensionWiringFactories)
    {
        this.extensionWiringFactories = extensionWiringFactories;
        this.modules = modules;
    }

    // ----------------------------------------------------------------------
    // Public methods
    // ----------------------------------------------------------------------

    public void configure( final Binder binder )
    {
        List<Wiring> wiringList = new ArrayList<Wiring>();
        for (WiringFactory wiringFactory : extensionWiringFactories)
        {
            wiringList.add(wiringFactory.create(binder));
        }

        // will bind any unbound with a generic bean locator
        wiringList.add(wiring(binder));

        final ElementAnalyzer analyzer = getAnalyzer( binder );
        for ( final Module m : modules )
        {
            for ( final Element e : Elements.getElements( m ) )
            {
                e.acceptVisitor( analyzer );
            }
        }

        analyzer.apply( wiringList );
    }

    // ----------------------------------------------------------------------
    // Customizable methods
    // ----------------------------------------------------------------------

    protected Wiring wiring( final Binder binder )
    {
        binder.install( new FileTypeConverter() );
        binder.install( new URLTypeConverter() );

        return new LocatorWiring( binder );
    }

    // ----------------------------------------------------------------------
    // Implementation methods
    // ----------------------------------------------------------------------

    ElementAnalyzer getAnalyzer( final Binder binder )
    {
        return new ElementAnalyzer( binder );
    }
}
