package machine

import java.util.*

val scanner = Scanner(System.`in`)

//store options in enum with all needs
enum class Coffee(val water: Int, val milk: Int, val beans: Int, val price: Int, val cups: Int) {
    ESPRESSO(250, 0, 16, 4, 1),
    LATTE(350, 75, 20, 7, 1),
    CAPPUCCINO(200, 100, 12, 6, 1)
}

//lets start program
fun main() {
    //on stock quantity to start
    val coffeeMachine = CoffeeMachine(400, 540, 120, 9, 550)

    while (true) {
        print("Write action (buy, fill, take, remaining, exit): ")
        when (readLine()!!) {
            "buy" -> coffeeMachine.buy()
            "fill" -> coffeeMachine.fill()
            "take" -> coffeeMachine.take()
            "remaining" -> coffeeMachine.printState()
            "exit" -> break
        }
    }
}

fun getIntInput(message: String): Int {
    print(message)
    return scanner.nextInt()
}

class CoffeeMachine(private var water: Int, private var milk: Int, private var beans: Int, private var cups: Int, private var dollar: Int) {

    //calculate stock and needs
    private fun make(coffee: Coffee) {
        if (water - coffee.water >= 0 && milk - coffee.milk >= 0 && beans - coffee.beans >= 0 && cups - coffee.cups >= 0){
            println("I have enough resources, making you a coffee!")
            water -= coffee.water
            milk -= coffee.milk
            beans -= coffee.beans
            cups -= coffee.cups
            dollar += coffee.price
        } else {
            if (water - coffee.water < 0) println("Sorry, not enough water!")
            if (milk - coffee.milk < 0) println("Sorry, not enough milk!")
            if (beans - coffee.beans < 0) println("Sorry, not enough beans!")
            if (cups - coffee.cups < 0) println("Sorry, not enough cups!")
        }
    }
    // state of stock
    fun printState() {
        println("The coffee machine has:")
        println("$water of water")
        println("$milk of milk")
        println("$beans of coffee beans")
        println("$cups of disposable cups")
        if (dollar == 0) println("$dollar of money")
        if (dollar > 0) println("$$dollar of money")
        println()
    }

    fun take() {
        println("I gave you $${dollar}")
        dollar = 0
    }
    //buying options with readLine input
    fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                when (readLine()!!) {
                    "1" -> make(Coffee.ESPRESSO)
                    "2" -> make(Coffee.LATTE)
                    "3" -> make(Coffee.CAPPUCCINO)
                    "back" -> return
                }
    }
    //refill stock option
    fun fill() {
        val newWater = getIntInput("Write how many ml of water do you want to add: ")
        water += newWater

        val newMilk = getIntInput("Write how many ml of milk do you want to add: ")
        milk += newMilk

        val newBeans = getIntInput("Write how many grams of coffee beans do you want to add: ")
        beans += newBeans

        val newCups = getIntInput("Write how many disposable cups of coffee do you want to add: ")
        cups += newCups
    }
}