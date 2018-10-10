package errai3d.client.local;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import jsinterop.base.Js;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageShowing;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.geometries.BoxBufferGeometry;
import org.treblereel.gwt.three4g.loaders.TextureLoader;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.materials.parameters.MeshBasicMaterialParameters;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.Scene;
import org.treblereel.gwt.three4g.textures.Texture;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;


/**
 * Main page
 *
 * @author kerbymart
 * @version 0-SNAPSHOT
 * @since 0-SNAPSHOT
 */
@Dependent
@Templated
@Page(role = DefaultPage.class, path = "/")
public class WelcomePage extends Composite {

    @Inject
    @DataField
    SimplePanel container;

    @PageShowing
    public void showing() {
        PerspectiveCamera camera = new PerspectiveCamera( 70, 0.1F, 1, 1000 );
        camera.position.z = 400;

        Scene scene = new Scene();

        Texture texture = new TextureLoader().load( "https://threejs.org/examples/textures/crate.gif");

        BoxBufferGeometry geometry = new BoxBufferGeometry( 200, 200, 200 );

        MeshBasicMaterialParameters meshBasicMaterialParameters = new MeshBasicMaterialParameters();
        meshBasicMaterialParameters.map = texture;

        MeshBasicMaterial material = new MeshBasicMaterial(meshBasicMaterialParameters);

        Mesh mesh = new Mesh(geometry, material);

        scene.add(mesh);

        WebGLRenderer webGLRenderer = new WebGLRenderer();

        webGLRenderer.domElement.id = "viewer";
        webGLRenderer.setPixelRatio(2.00);
        webGLRenderer.setSize(500.00, 500.00);
        webGLRenderer.render(scene, camera);
        Element element = Js.cast(webGLRenderer.domElement);
        container.getElement().appendChild(element);
    }

}
