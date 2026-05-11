package scenarioBased.part2;/*
 * Question 25: Create a package TYBSc which will have 2 classes as:
 * class Mathematics with methods to:
 *   - add two numbers
 *   - add three float numbers
 * class Maximum with a method to find maximum of three numbers.
 * (Make use of finalize)
 *
 * NOTE: This file should be placed in a TYBSc/ directory.
 * Compile with: javac TYBSc/Mathematics.java TYBSc/Maximum.java
 * Then compile the main: javac Q25_PackageDemo.java
 * Run with: java Q25_PackageDemo
 */

// ---- File: TYBSc/Mathematics.java ----
// package TYBSc;
// public class Mathematics {
//     public int add(int a, int b) { return a + b; }
//     public float add(float a, float b, float c) { return a + b + c; }
//     protected void finalize() { System.out.println("Mathematics object destroyed"); }
// }

// ---- File: TYBSc/Maximum.java ----
// package TYBSc;
// public class Maximum {
//     public int findMax(int a, int b, int c) {
//         return Math.max(a, Math.max(b, c));
//     }
//     protected void finalize() { System.out.println("Maximum object destroyed"); }
// }

// Since we can't split into package files in a single file,
// the code below demonstrates the equivalent functionality inline
// with proper package structure instructions above.

public class Q25_PackageDemo {

	// Inline implementation of TYBSc.Mathematics
	static class Mathematics {
		// Add two integers
		int add(int a, int b) {
			return a + b;
		}

		// Overloaded: Add three floats
		float add(float a, float b, float c) {
			return a + b + c;
		}

		// finalize method - called by garbage collector before object is destroyed
		@Override
		protected void finalize() throws Throwable {
			System.out.println("Mathematics object finalized (destroyed by GC).");
			super.finalize();
		}
	}

	// Inline implementation of TYBSc.Maximum
	static class Maximum {
		// Find maximum of three numbers
		int findMax(int a, int b, int c) {
			if (a >= b && a >= c)
				return a;
			else if (b >= a && b >= c)
				return b;
			else
				return c;
		}

		@Override
		protected void finalize() throws Throwable {
			System.out.println("Maximum object finalized (destroyed by GC).");
			super.finalize();
		}
	}

	public static void main(String[] args) {
		Mathematics math = new Mathematics();
		Maximum max = new Maximum();

		// Test Mathematics methods
		int sumInt = math.add(10, 20);
		float sumFloat = math.add(1.5f, 2.3f, 3.7f);
		System.out.println("Sum of 10 and 20: " + sumInt);
		System.out.printf("Sum of 1.5, 2.3, 3.7: %.1f%n", sumFloat);

		// Test Maximum method
		int maxVal = max.findMax(45, 78, 23);
		System.out.println("Maximum of 45, 78, 23: " + maxVal);

		// Nullify references and suggest garbage collection
		math = null;
		max = null;
		System.gc(); // Suggest GC to invoke finalize
		System.runFinalization();
		System.out.println("Objects set to null and GC suggested.");
	}
}
