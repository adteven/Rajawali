package c.org.rajawali3d.scene.graph;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import c.org.rajawali3d.annotations.RequiresReadLock;
import c.org.rajawali3d.annotations.RequiresWriteLock;
import c.org.rajawali3d.bounds.AABB;
import c.org.rajawali3d.camera.Camera;
import c.org.rajawali3d.object.RenderableObject;
import org.rajawali3d.util.Intersector.Intersection;

/**
 * Interface to be implemented by classes which will be attached to {@link SceneNode}s. These could be 3D render
 * objects, cameras, lights, etc.
 *
 * @author Jared Woolston (Jared.Woolston@gmail.com)
 */
public interface NodeMember extends AABB, RenderableObject {

    /**
     * Sets the {@link NodeParent} of this {@link NodeMember}.
     *
     * @param parent {@link NodeParent} implementation. Can be null.
     * @throws InterruptedException Thrown if the calling thread was interrupted while waiting for lock acquisition.
     */
    void setParent(@Nullable NodeParent parent) throws InterruptedException;

    /**
     * Called by a {@link NodeParent} when the model matrices (local and world) have been updated. This is an
     * opportunity for members to make any updates that depend on this information, for example, recalculating the
     * view matrix of a {@link Camera} instance.
     */
    @RequiresWriteLock void modelMatrixUpdated();

    /**
     * Determines the intersection between these bounds and the provided {@link AABB}.
     *
     * @param bounds The bounds to test intersection with.
     * @return {@link Intersection} The type of intersection.
     */
    @RequiresReadLock @Intersection int intersectBounds(@NonNull AABB bounds);
}
