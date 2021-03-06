package org.fxmisc.wellbehaved.skin;

/**
 * Represents the controller aspect of a JavaFX control. It defines how the
 * control reacts to user input. A Behavior typically registers event handlers
 * on the control. It may hold a reference to the {@link Visual} in case it
 * needs to query its state in order to properly implement the behavior.
 *
 * @deprecated Since 0.3. We have come to believe that skins, as designed in
 * JavaFX, are not very useful and not worth the trouble. Package
 * {@link org.fxmisc.wellbehaved.skin} will be removed in a future version.
 */
@Deprecated
public interface Behavior {
    /**
     * Called to release resources associated with this Behavior when it is no
     * longer being used, in particular to stop observing the control and the
     * Visual, i.e. remove any event handlers, listeners, etc.
     */
    void dispose();
}
