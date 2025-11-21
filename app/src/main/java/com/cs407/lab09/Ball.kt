package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // TODO: Call reset()
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        val updatedVelocityX : Float = velocityX + (1f/2f) * (xAcc + accX) * dT
        val updatedVelocityY : Float = velocityY + (1f/2f) * (yAcc + accY) * dT
        val updatedDistTravX: Float = velocityX * dT + (1f/6f) * dT * dT * (3 * accX + xAcc)
        val updatedDistTravY: Float = velocityY * dT + (1f/6f) * dT * dT * (3 * accY + yAcc)

        velocityX = updatedVelocityX
        velocityY = updatedVelocityY
        posX += updatedDistTravX
        posY += updatedDistTravY
        accX = xAcc
        accY = yAcc

        checkBoundaries()

    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
        // Check Left Wall
        if (posX < 0) {
            // Only stop movement if trying to go further left
            if (velocityX < 0) velocityX = 0f
            if (accX < 0) accX = 0f
        }
        // Check Right Wall
        if (posX + ballSize > backgroundWidth) {
            // Only stop movement if trying to go further right
            if (velocityX > 0) velocityX = 0f
            if (accX > 0) accX = 0f
        }

        // Check Top Wall
        if (posY < 0) {
            // Only stop movement if trying to go further up
            if (velocityY < 0) velocityY = 0f
            if (accY < 0) accY = 0f
        }
        // Check Bottom Wall
        if (posY + ballSize > backgroundHeight) {
            // Only stop movement if trying to go further down
            if (velocityY > 0) velocityY = 0f
            if (accY > 0) accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = (backgroundWidth - ballSize) / 2f
        posY = (backgroundHeight - ballSize) / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}