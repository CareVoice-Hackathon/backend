# Head-to-Toe Assessment Speech-to-Text Converter

This project converts audio files to text using the Deepgram API, specifically designed for medical head-to-toe assessment transcriptions.

## Components

- `transcribe_audio.py`: Main script using Deepgram REST API to transcribe audio files
- `demo_htt_speech_voice.mp3`: Audio file generated from text using Amazon Polly
- `htt_voice_to_text_output.txt`: Output file containing the transcribed text

## Setup

1. Install required packages:
```bash
pip install deepgram-sdk python-dotenv
```

2. Create a `.env` file in the project root and add your Deepgram API key:
```
DEEPGRAM_API_KEY=your_api_key_here
```

3. Get a Deepgram API key:
   - Sign up at [console.deepgram.com](https://console.deepgram.com/signup)
   - Create a new API key
   - Copy the key to your `.env` file

## Usage

1. Place your audio file (MP3 format) in the project directory
2. Run the transcription script:
```bash
python transcribe_audio.py
```
3. The transcribed text will be saved to `htt_voice_to_text_output.txt`

## Features

- Uses Deepgram's nova-3 model for accurate medical terminology transcription
- Smart formatting for better readability
- Supports MP3 audio format
- Environment-based configuration for secure API key storage

## Notes

- The audio file should be clear and in MP3 format
- Internet connection required for API access
- Make sure your Deepgram API key has sufficient credits