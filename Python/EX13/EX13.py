"""
Functions which create Mandelbrot and Julia sets and prints them out as .PNG files.

@author: Ragnar Luga.
"""

from PIL import Image


def juliafractal(imgx, imgy, c, maxiter):
    """
    Create the Julia fractal set taking in a custom c value and printing it out as PNG picture.

    Arguments:
    c - a custom complex number inputted by the user.
    imgx - the width of the .PNG picture.
    imgyy - the height of the .PNG picture.
    maxiter - the maximum amount of iterations done with each pixel.
    Output:
    PNG picture with the resulting set.
    """
    image = Image.new("RGB", (imgx, imgy))
    xa = -2.0
    xb = 2.0
    ya = -2.0
    yb = 2.0
    for y in range(imgy):
        zy = y * (yb - ya) / imgy + ya
        for x in range(imgx):
            zx = x * (xb - xa) / imgx + xa
            z = zx + zy * 1j
            for i in range(maxiter):
                if abs(z) > 2.0:
                    break
                z = z * z + c
            image.putpixel((x, y), (i % 8 * 32, i % 16 * 16, i % 32 * 8))
    image.save("juliaFractal.png")


def mandel(imgx, imgy, maxiter):
    """
    Create the Mandelbrot fractal set  and printing it out as PNG picture.

    Arguments:
    imgx - the width of the .PNG picture.
    imgyy - the height of the .PNG picture.
    maxiter - the maximum amount of iterations done with each pixel.
    Output:
    PNG picture with the resulting set.
    """
    image = Image.new("RGB", (imgx, imgy))
    xa = -2.0
    xb = 2.0
    ya = -2.0
    yb = 2.0
    for y in range(imgy):
        zy = y * (yb - ya) / imgy + ya
        for x in range(imgx):
            zx = x * (xb - xa) / imgx + xa
            z = zx + zy * 1j
            c = z
            for i in range(maxiter):
                z = z * z + c
                if abs(z) > 2.0:
                    break
            image.putpixel((x, y), (i % 8 * 32, i % 16 * 16, i % 32 * 8))
    image.save("mandelbrotFractal.png")


if __name__ == "__main__":
    juliafractal(500, 500, -1.476 + 0j, 400)
    mandel(400, 400, 100)
