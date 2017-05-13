package org.sidescroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.data.CompositeItemVO;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.resources.IResourceRetriever;
import com.uwsoft.editor.renderer.scene2d.CompositeActor;

/**
 * Created by matts on 13/05/17.
 */
public class UIStage extends Stage {

    public UIStage(IResourceRetriever iResourceRetriever){

        Gdx.input.setInputProcessor(this);

        ProjectInfoVO projectInfo = iResourceRetriever.getProjectVO();

        CompositeItemVO specialButton = projectInfo.libraryItems.get("specialbutton");
        CompositeActor buttonActor = new CompositeActor(specialButton, iResourceRetriever);

        addActor(buttonActor);

        buttonActor.setX(getWidth() - buttonActor.getWidth() - 15);
        buttonActor.setY(getHeight() - buttonActor.getHeight());

        buttonActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Hi");
            }
        });
    }
}
