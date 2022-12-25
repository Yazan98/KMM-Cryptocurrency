import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()

	var body: some View {
        if CoinaApplicationUtils.isUserLoggedIn() {
            Text(greet)
            Text("Is User Logged In ? \(CoinaApplicationUtils.isUserLoggedIn())" as String)
        } else {
            LoginScreen()
        }
		
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
