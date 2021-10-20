// See LICENSE.txt for license details.
package riscv

import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val examples = Map(
      "And" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new And(), manager) {
          (c) => new AndTests(c)
        }
      },
	"Mux2" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Mux2(), manager) {
          (c) => new Mux2Tests(c)
        }
      },
	"Adder32" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Adder32(), manager) {
          (c) => new Adder32Tests(c)
        }
      },
      "Subtractor32" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Subtractor32(), manager) {
          (c) => new Subtractor32Tests(c)
        }
      },
	 "Mux2easy" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Mux2easy(), manager) {
          (c) => new Mux2easyTests(c)
        }
      }
	
      
      
      
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner("examples", examples, args)
  }
}

