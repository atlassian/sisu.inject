package org.eclipse.sisu.scanners.module;

import org.eclipse.sisu.scanners.AbstractClassFinder;

/**
 * Finds all instances of {@link ModuleFactory}
 */
public final class SisuModuleFinder extends AbstractClassFinder
{
    public SisuModuleFinder(boolean global)
    {
        super(global, ModuleFactory.class.getName());
    }
}
