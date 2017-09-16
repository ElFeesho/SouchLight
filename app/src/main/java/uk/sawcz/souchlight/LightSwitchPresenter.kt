package uk.sawcz.souchlight

class LightSwitchPresenter(private val view: View, private val model: Model) {
    interface View {
        fun disable()
        fun enable()

        fun on()
        fun off()
        fun onSwitchOn(cb: () -> Unit)
        fun onSwitchOff(cb: () -> Unit)
    }

    interface Model {
        fun updateStatus(cb:(Boolean)->Unit)
        fun turnOn()
        fun turnOff()
    }

    init {
        view.disable()

        model.updateStatus {
            view.enable()
            when (it) {
                true -> view.on()
                false -> view.off()
            }
        }

        view.onSwitchOn {
            model.turnOn()
        }

        view.onSwitchOff {
            model.turnOff()
        }
    }
}