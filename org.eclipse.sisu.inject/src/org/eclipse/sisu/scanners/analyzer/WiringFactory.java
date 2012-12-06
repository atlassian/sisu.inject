package org.eclipse.sisu.scanners.analyzer;

import com.google.inject.Binder;
import com.google.inject.spi.ElementVisitor;
import org.eclipse.sisu.binders.Wiring;
import org.osgi.framework.Bundle;

/**
 * Extension point to allow third-parties to plug new handlers for unresolved object references
 */
public interface WiringFactory
{
    /**
     * Creates a {@link Wiring} instance
     *
     * @param binder The binder.  Cannot be null.
     * @return The {@link Wiring} instance
     */
    Wiring create(Binder binder);
}
