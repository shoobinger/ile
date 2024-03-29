package com.josrv.ile.gui.component

import com.josrv.ile.gui.state.IleState
import com.josrv.ile.gui.state.data.Token
import javafx.scene.Cursor
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class IleLabel(
    override var localState: Token,
    clickHandler: (MouseEvent) -> Unit
) : Label(localState.value), IleBlock<Token> {

    private val normalFont = Font.font("Nimbus Mono PS", FontWeight.SEMI_BOLD, 16.0)
    private val selectedFont = Font.font("Nimbus Mono PS", FontWeight.BOLD, 16.0)
    private val normalBackground = Background(BackgroundFill(Color.TRANSPARENT, null, null))
    private val hoverBackground = Background(BackgroundFill(Color.rgb(0, 0, 0, 0.1), null, null))

    private val setHoveredStyle = { _: MouseEvent ->
        scene.cursor = Cursor.HAND
        background = hoverBackground
    }

    private val setNotHoveredStyle = { _: MouseEvent ->
        scene.cursor = Cursor.DEFAULT
        background = normalBackground
    }

    init {
        setNormalStyle()
        setOnMouseClicked(clickHandler)
        setOnMouseEntered(setHoveredStyle)
        setOnMouseExited(setNotHoveredStyle)
    }

    override fun redrawComponent(state: Token) {
        if (state.selected) {
            setSelectedStyle()
        } else {
            setNormalStyle()
        }
    }

    override fun getStateSlice(state: IleState): Token {
        return state.page.tokens[localState.index]
    }

    private fun setNormalStyle() {
        this.font = normalFont
    }

    private fun setSelectedStyle() {
        this.font = selectedFont
    }
}