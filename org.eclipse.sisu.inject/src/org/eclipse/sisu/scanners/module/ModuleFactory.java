package org.eclipse.sisu.scanners.module;

import com.google.inject.Module;
import org.osgi.framework.Bundle;

/**
 * Extension point for adding default modules to a new Injector instance
 */
public interface ModuleFactory
{
    /**
     * Gets the module to install into the binder
     *
     * @param bundle The OSGi bundle of the target of the Injector
     * @return The module instance, cannot be null
     */
    Module getModule(Bundle bundle);
}
